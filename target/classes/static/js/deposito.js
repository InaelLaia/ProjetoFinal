document.addEventListener('DOMContentLoaded', function() {
    const depositoSelect = document.getElementById('deposito');
    const tabelaBody = document.querySelector('#tabela-produtos tbody');
    const btnExcluir = document.getElementById('btn-excluir');
    let selectedRow = null;
    let selectedRowData = null;
    
    depositoSelect.addEventListener('change', function() {
        const depositoSelecionado = this.value;
        
        if (depositoSelecionado) {
            carregarProdutosDeposito(depositoSelecionado);
        } else {
            tabelaBody.innerHTML = '';
            btnExcluir.disabled = true;
            selectedRow = null;
            selectedRowData = null;
        }
    });
    
    function carregarProdutosDeposito(deposito) {
        tabelaBody.innerHTML = '<tr><td colspan="3">Carregando...</td></tr>';
        
        fetch(`/api/produtos-deposito?deposito=${encodeURIComponent(deposito)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao carregar produtos');
                }
                return response.json();
            })
            .then(produtos => {
                tabelaBody.innerHTML = '';
                
                if (produtos && produtos.length > 0) {
                    produtos.forEach(produto => {
                        const row = document.createElement('tr');
                        const nome = produto[0];
                        const pontoReposicao = produto[1];
                        const quantidadeAtual = produto[2];
                        
                        const precisaReposicao = pontoReposicao >= quantidadeAtual;
                        
                        row.innerHTML = `
                            <td>${nome}</td>
                            <td>${pontoReposicao}</td>
                            <td style="color: ${precisaReposicao ? 'red' : 'black'}">${quantidadeAtual}</td>
                        `;
                        
                        row.dataset.nome = nome;
                        row.dataset.quantidade = quantidadeAtual;
                        
                        row.addEventListener('click', function() {
                            if (selectedRow) {
                                selectedRow.style.backgroundColor = '';
                            }
                            
                            this.style.backgroundColor = '#e0e0e0';
                            selectedRow = this;
                            selectedRowData = {
                                nome: this.dataset.nome,
                                quantidade: parseInt(this.dataset.quantidade)
                            };
                            
                            btnExcluir.disabled = selectedRowData.quantidade > 0;
                        });
                        
                        tabelaBody.appendChild(row);
                    });
                } else {
                    tabelaBody.innerHTML = '<tr><td colspan="3">Nenhum produto encontrado neste depósito</td></tr>';
                }
            })
            .catch(error => {
                console.error('Erro:', error);
                tabelaBody.innerHTML = '<tr><td colspan="3">Erro ao carregar produtos</td></tr>';
            });
    }
    
    btnExcluir.addEventListener('click', function() {
        if (!selectedRow || !selectedRowData) {
            alert('Selecione um produto para excluir');
            return;
        }
        
        const nomeProduto = selectedRowData.nome;
        const quantidadeAtual = selectedRowData.quantidade;
        const depositoSelecionado = depositoSelect.value;
        
        if (quantidadeAtual > 0) {
            alert('Não é possível excluir um produto com quantidade maior que 0.');
            return;
        }
        
        if (confirm(`Deseja mesmo excluir o produto "${nomeProduto}" do depósito?`)) {
            fetch(`/api/produto-deposito?deposito=${encodeURIComponent(depositoSelecionado)}&produto=${encodeURIComponent(nomeProduto)}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error('Erro na resposta do servidor');
                }
            })
            .then(message => {
                alert('Produto excluído com sucesso');
                carregarProdutosDeposito(depositoSelecionado);
                btnExcluir.disabled = true;
                selectedRow = null;
                selectedRowData = null;
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Erro ao excluir produto: ' + error.message);
            });
        }
    });
});