document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('form-produto-deposito');
    const deposito = document.getElementById('deposito');
    const produto = document.getElementById('produto');
    const pontoReposicao = document.getElementById('ponto-reposicao');

    form.addEventListener('submit', function(event) {
        let isValid = true;
        let errorMessage = '';

        if (!deposito.value) {
            isValid = false;
            errorMessage = 'Por favor, selecione um depósito.';
        } else if (!produto.value) {
            isValid = false;
            errorMessage = 'Por favor, selecione um produto.';
        } else if (!pontoReposicao.value || parseInt(pontoReposicao.value) < 0) {
            isValid = false;
            errorMessage = 'O ponto de reposição deve ser um número positivo.';
        }

        if (!isValid) {
            event.preventDefault();
            alert(errorMessage);
        }
    });
});
