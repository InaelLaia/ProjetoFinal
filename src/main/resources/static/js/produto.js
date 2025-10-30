document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('form-produto');
    const btnVoltar = document.getElementById('btn-voltar');
    const nome = document.getElementById('nome');
    const marca = document.getElementById('marca');
    const preco = document.getElementById('preco');

    // Botão voltar
    btnVoltar.addEventListener('click', function(event) {
        event.preventDefault();
        window.location.href = '/';
    });

    // Validação do formulário antes do envio
    form.addEventListener('submit', function(event) {
        let isValid = true;
        let errorMessage = '';

        if (!nome.value.trim()) {
            isValid = false;
            errorMessage = 'Por favor, informe o nome do produto.';
        } else if (!marca.value.trim()) {
            isValid = false;
            errorMessage = 'Por favor, informe a marca do produto.';
        } else if (!preco.value || parseFloat(preco.value) <= 0) {
            isValid = false;
            errorMessage = 'O preço deve ser maior que zero.';
        }

        if (!isValid) {
            event.preventDefault();
            alert(errorMessage);
            return;
        }

        alert('Produto cadastrado com sucesso!');
    });
});
