document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('form-deposito');
    const nome = document.getElementById('nome');
    const telefone = document.getElementById('telefone');
    const rua = document.getElementById('rua');
    const numero = document.getElementById('numero');
    const bairro = document.getElementById('bairro');
    const cidade = document.getElementById('cidade');
    const uf = document.getElementById('uf');

    form.addEventListener('submit', function(event) {
        let isValid = true;
        let errorMessage = '';

        if (!nome.value.trim()) {
            isValid = false;
            errorMessage = 'Por favor, informe o nome do depósito.';
        } else if (!telefone.value.trim()) {
            isValid = false;
            errorMessage = 'Por favor, informe o telefone.';
        } else if (!rua.value.trim()) {
            isValid = false;
            errorMessage = 'Por favor, informe a rua.';
        } else if (!numero.value) {
            isValid = false;
            errorMessage = 'Por favor, informe o número.';
        } else if (!bairro.value.trim()) {
            isValid = false;
            errorMessage = 'Por favor, informe o bairro.';
        } else if (!cidade.value.trim()) {
            isValid = false;
            errorMessage = 'Por favor, informe a cidade.';
        } else if (!uf.value) {
            isValid = false;
            errorMessage = 'Por favor, selecione o UF.';
        }

        if (!isValid) {
            event.preventDefault();
            alert(errorMessage);
        }
    });
});
