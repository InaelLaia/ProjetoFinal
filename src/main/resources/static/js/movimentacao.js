document.addEventListener('DOMContentLoaded', function () {
    const depositoSelect = document.getElementById('deposito');
    const produtoSelect = document.getElementById('produto');

    depositoSelect.addEventListener('change', function () {
        const depositoSelecionado = this.value;

        if (depositoSelecionado) {
            produtoSelect.disabled = false;
            produtoSelect.innerHTML = '<option value="" selected disabled>Carregando produtos...</option>';

            fetch(`/api/produtos?deposito=${encodeURIComponent(depositoSelecionado)}`)
                .then(response => {
                    if (!response.ok) throw new Error('Erro na resposta da API');
                    return response.json();
                })
                .then(produtos => {
                    if (produtos && produtos.length > 0) {
                        produtoSelect.innerHTML = '<option value="" selected disabled>Selecione um produto</option>';
                        produtos.forEach(produto => {
                            const option = document.createElement('option');
                            option.value = produto;
                            option.textContent = produto;
                            produtoSelect.appendChild(option);
                        });
                    } else {
                        produtoSelect.innerHTML = '<option value="" selected disabled>Nenhum produto encontrado neste depósito</option>';
                        produtoSelect.disabled = true;
                    }
                })
                .catch(error => {
                    console.error('Erro ao carregar produtos:', error);
                    produtoSelect.innerHTML = '<option value="" selected disabled>Erro ao carregar produtos</option>';
                    produtoSelect.disabled = true;
                });
        } else {
            produtoSelect.innerHTML = '<option value="" selected disabled>Primeiro selecione um depósito</option>';
            produtoSelect.disabled = true;
        }
    });

    document.getElementById('form-movimentacao').addEventListener('submit', function (event) {
        const deposito = document.getElementById('deposito');
        const produto = document.getElementById('produto');
        const quantidade = document.getElementById('quantidade');
        const tipo = document.getElementById('tipo');

        let isValid = true;
        let errorMessage = '';

        if (!deposito.value) {
            isValid = false;
            errorMessage = 'Por favor, selecione um depósito.';
        } else if (!produto.value || produto.disabled) {
            isValid = false;
            errorMessage = 'Por favor, selecione um produto.';
        } else if (!quantidade.value || parseInt(quantidade.value) <= 0) {
            isValid = false;
            errorMessage = 'A quantidade deve ser maior que zero.';
        } else if (!tipo.value) {
            isValid = false;
            errorMessage = 'Por favor, selecione o tipo de movimentação.';
        }

        if (!isValid) {
            event.preventDefault();
            alert(errorMessage);
        }
    });

    const btnVoltar = document.getElementById('btn-voltar');
    if (btnVoltar) {
        btnVoltar.addEventListener('click', function () {
            window.location.href = 'index.html';
        });
    }
});
