//function updateQuantity(input) {
//    let itemId = input.getAttribute("data-item-id");
//    let quantity = input.value;
//
//    $.ajax({
//        url: `/cart/update/${itemId}?quantity=${quantity}`,
//        type: 'PUT',
//        success: function(response) {
//            console.log(response);
//            // Cập nhật thành công, tải lại trang
//            location.reload();
//        },
//        error: function(xhr, status, error) {
//            console.log(error);
//            // Xảy ra lỗi, xử lý lỗi (nếu cần)
//        }
//    });
//}
function updateQuantity(input) {
    let itemId = input.getAttribute("data-item-id");
    let quantity = input.value;

    $.ajax({
        url: `/cart/update/${itemId}?quantity=${quantity}`,
        type: 'PUT',
        success: function (response) {
            console.log(response);
            updateSubtotalAndTotal(); // Cập nhật lại subtotal và total sau khi cập nhật số lượng thành công
            location.reload();
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}

$(document).ready(function() {
        $('#update-cart-btn').click(function(event) {
            event.preventDefault();
            updateSubtotalAndTotal();
        });
    });

    function updateSubtotalAndTotal() {
        let productRows = $('.table-body-row');
        let subtotal = 0;

        productRows.each(function() {
            let productPrice = parseFloat($(this).find('.product-price').text());
            let quantity = parseInt($(this).find('.product-quantity input').val());

            if (!isNaN(productPrice) && !isNaN(quantity)) {
                subtotal += productPrice * quantity;
            }
        });

        let total = subtotal + 45000; // Assuming the shipping cost is always 45,000 đồng

        if (!isNaN(subtotal) && isFinite(subtotal)) {
            $('#subtotal').text(subtotal.toFixed(2));
        }

        if (!isNaN(total) && isFinite(total)) {
            $('#total').text(total.toFixed(2));
        }

        // Store the values in Local Storage
        localStorage.setItem('subtotal', subtotal.toFixed(2));
        localStorage.setItem('total', total.toFixed(2));
    }

    // Check if subtotal and total values are stored in Local Storage
    if (localStorage.getItem('subtotal') && localStorage.getItem('total')) {
        let subtotal = localStorage.getItem('subtotal');
        let total = localStorage.getItem('total');

        $('#subtotal').text(subtotal);
        $('#total').text(total);
    }