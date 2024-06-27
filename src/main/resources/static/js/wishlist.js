document.addEventListener('DOMContentLoaded', function() {
    loadWishlist();
});

function loadWishlist() {
    fetch('/api/wishlist')
        .then(response => response.json())
        .then(data => {
            const wishlistTable = document.getElementById('wishlist-table').getElementsByTagName('tbody')[0];
            wishlistTable.innerHTML = '';
            data.forEach(stock => {
                const row = `<tr>
                                <td>${stock.companyName} (${stock.stockSymbol})</td>
                                <td>$${stock.targetPrice.toFixed(2)}</td>
                                <td>${stock.notes}</td>
                                <td><button class="btn add-to-portfolio" onclick="openModal(${stock.id})">Add to Portfolio</button></td>
                            </tr>`;
                wishlistTable.insertAdjacentHTML('beforeend', row);
            });
        });
}

function openModal(id) {
    const modal = document.getElementById('myModal');
    modal.style.display = 'block';
    modal.dataset.stockId = id;
}

function closeModal() {
    document.getElementById('myModal').style.display = 'none';
}

function addToPortfolio() {
    const id = document.getElementById('myModal').dataset.stockId;
    fetch(`/api/wishlist/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        closeModal();
        loadWishlist();
        loadPortfolio();
    });
}
