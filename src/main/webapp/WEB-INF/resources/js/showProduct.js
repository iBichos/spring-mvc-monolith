$('.product').click(function() {
    location.href = `/product/${$(this).find('#productId').text()}`
});