function removeProduct(event, productId) {
    event.preventDefault();
    $.ajax({
        url: '/cart/remove/' + productId,
        type: 'DELETE',
        success: function (response) {
            location.reload(); // Tải lại trang sau khi xóa thành công
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}
