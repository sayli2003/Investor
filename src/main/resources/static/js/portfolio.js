document.addEventListener('DOMContentLoaded', function() {
    loadPortfolio();
});

// function loadPortfolio() {
//     fetch('/api/portfolio')
//         .then(response => response.json())
//         .then(data => {
//             const portfolioTable = document.getElementById('portfolio-table').getElementsByTagName('tbody')[0];
//             portfolioTable.innerHTML = '';
//             data.forEach(stock => {
//                 const row = `<tr>
//                                 <td>(${stock.stockSymbol})</td>
//                                 <td>${stock.quantity}</td>
//                                 <td>$${stock.purchasePrice.toFixed(2)}</td>
//                                 <td>$${(stock.quantity * stock.currentPrice).toFixed(2)}</td>
//                                 <td><button class="btn remove-from-portfolio" onclick="openModal(${stock.id})">Remove</button></td>
//                             </tr>`;
//                 portfolioTable.insertAdjacentHTML('beforeend', row);
//             });
//         });
// }

function openModal(id) {
    const modal = document.getElementById('myModal');
    modal.style.display = 'block';
    modal.dataset.stockId = id;
}

function closeModal() {
    document.getElementById('myModal').style.display = 'none';
}

function removeStock() {
    const id = document.getElementById('myModal').dataset.stockId;
    fetch(`/api/portfolio/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        closeModal();
        loadPortfolio();
    });
}
