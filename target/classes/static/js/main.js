document.addEventListener('DOMContentLoaded', function() {
    const btnNova = document.getElementById('btn-nova');
    const btnProduto = document.getElementById('btn-produto');
    const btnFornecedor = document.getElementById('btn-fornecedor');
    const btnDeposito = document.getElementById('btn-deposito');

    if (btnNova) {
        btnNova.addEventListener('click', function() {
            window.location.href = '/movimentacao';
        });
    }

    if (btnProduto) {
        btnProduto.addEventListener('click', function() {
            window.location.href = '/produto';
        });
    }

    if (btnFornecedor) {
        btnFornecedor.addEventListener('click', function() {
            window.location.href = '/fornecedor';
        });
    }

    if (btnDeposito) {
        btnDeposito.addEventListener('click', function() {
            window.location.href = '/deposito';
        });
    }
});
