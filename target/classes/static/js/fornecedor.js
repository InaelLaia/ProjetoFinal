document.addEventListener('DOMContentLoaded', function() {
    const fornecedorSelect = document.getElementById('fornecedor');
    const produtoSelect = document.getElementById('produto');
    const btnAdicionar = document.getElementById('btn-adicionar');
    const btnExcluir = document.getElementById('btn-excluir');
    const tabelaBody = document.querySelector('#tabela-produtos tbody');
    let selectedRow = null;
    let selectedRowData = null;
    
    fornecedorSelect.addEventListener('change', function() {
        const fornecedorSelecionado = this.value;
        
        if (fornecedorSelecionado) {
            carregarProdutosFornecedor(fornecedorSelecionado);
            carregarProdutosDisponiveis(fornecedorSelecionado);
            produtoSelect.disabled = false;
        } else {
            limparTabela();
            produtoSelect.innerHTML = '<option value="" selected disabled>Selecione um produto</option>';
            produtoSelect.disabled = true;
            btnAdicionar.disabled = true;
            btnExcluir.disabled = true;
        }
    });
    
    produtoSelect.addEventListener('change', function() {
        btnAdicionar.disabled = !this.value;
    });
    
    function carregarProdutosFornecedor(fornecedor) {
        tabelaBody.innerHTML = '<tr><td colspan="3">Carregando...</td></tr>';
        
        fetch(`/api/fornecedor/produtos-fornecedor?fornecedor=${encodeURIComponent(fornecedor)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao carregar produtos');
                }
                return response.json();
            })
            .then(produtos => {
                preencherTabelaProdutos(produtos);
            })
            .catch(error => {
                console.error('Erro:', error);
                tabelaBody.innerHTML = '<tr><td colspan="3">Erro ao carregar produtos</td></tr>';
            });
    }
    
    function carregarProdutosDisponiveis(fornecedor) {
        produtoSelect.innerHTML = '<option value="" selected disabled>Carregando produtos...</option>';
        
        fetch(`/api/fornecedor/produtos-disponiveis?fornecedor=${encodeURIComponent(fornecedor)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao carregar produtos disponíveis');
                }
                return response.json();
            })
            .then(produtos => {
                produtoSelect.innerHTML = '<option value="" selected disabled>Selecione um produto</option>';
                
                if (produtos && produtos.length > 0) {
                    produtos.forEach(produto => {
                        const option = document.createElement('option');
                        option.value = produto;
                        option.textContent = produto;
                        produtoSelect.appendChild(option);
                    });
                } else {
                    produtoSelect.innerHTML = '<option value="" selected disabled>Nenhum produto disponível</option>';
                }
            })
            .catch(error => {
                console.error('Erro:', error);
                produtoSelect.innerHTML = '<option value="" selected disabled>Erro ao carregar produtos</option>';
            });
    }
    
    function preencherTabelaProdutos(produtos) {
        tabelaBody.innerHTML = '';
        
        if (produtos && produtos.length > 0) {
            produtos.forEach(produto => {
                const row = document.createElement('tr');
                const nome = produto[0];
                const marca = produto[1];
                const preco = produto[2];
                
                row.innerHTML = `
                    <td>${nome}</td>
                    <td>${marca}</td>
                    <td>R$ ${parseFloat(preco).toFixed(2)}</td>
                `;
                
                row.dataset.nome = nome;
                
                row.addEventListener('click', function() {
                    selecionarLinha(this, nome);
                });
                
                tabelaBody.appendChild(row);
            });
        } else {
            tabelaBody.innerHTML = '<tr><td colspan="3">Nenhum produto encontrado para este fornecedor</td></tr>';
        }
        
        btnExcluir.disabled = true;
        selectedRow = null;
        selectedRowData = null;
    }
    
    function selecionarLinha(linha, nomeProduto) {
        if (selectedRow) {
            selectedRow.style.backgroundColor = '';
        }
        
        linha.style.backgroundColor = '#e0e0e0';
        selectedRow = linha;
        selectedRowData = {
            nome: nomeProduto
        };
        
        btnExcluir.disabled = false;
    }
    
    function limparTabela() {
        tabelaBody.innerHTML = '';
        btnAdicionar.disabled = true;
        btnExcluir.disabled = true;
        selectedRow = null;
        selectedRowData = null;
    }
    
    btnAdicionar.addEventListener('click', function() {
        const fornecedorSelecionado = fornecedorSelect.value;
        const produtoSelecionado = produtoSelect.value;
        
        if (!fornecedorSelecionado || !produtoSelecionado) {
            alert('Selecione um fornecedor e um produto');
            return;
        }
        
        const formData = new FormData();
        formData.append('fornecedor', fornecedorSelecionado);
        formData.append('produto', produtoSelecionado);
        
        fetch('/fornecedor/adicionar', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (response.ok) {
                alert('Produto adicionado ao fornecedor com sucesso!');
                carregarProdutosFornecedor(fornecedorSelecionado);
                carregarProdutosDisponiveis(fornecedorSelecionado);
                produtoSelect.value = '';
                btnAdicionar.disabled = true;
            } else {
                alert('Erro ao adicionar produto ao fornecedor');
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao adicionar produto ao fornecedor');
        });
    });
    
    btnExcluir.addEventListener('click', function() {
        if (!selectedRow || !selectedRowData) {
            alert('Selecione um produto para excluir');
            return;
        }
        
        const nomeProduto = selectedRowData.nome;
        const fornecedorSelecionado = fornecedorSelect.value;
        
        if (!confirm(`Deseja mesmo excluir o produto "${nomeProduto}" do fornecedor?`)) {
            return;
        }
        
        const formData = new FormData();
        formData.append('fornecedor', fornecedorSelecionado);
        formData.append('produto', nomeProduto);
        
        fetch('/fornecedor/excluir', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (response.ok) {
                alert('Produto excluído do fornecedor com sucesso!');
                carregarProdutosFornecedor(fornecedorSelecionado);
                carregarProdutosDisponiveis(fornecedorSelecionado);
                btnExcluir.disabled = true;
                selectedRow = null;
                selectedRowData = null;
            } else {
                alert('Erro ao excluir produto do fornecedor');
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao excluir produto do fornecedor');
        });
    });
});