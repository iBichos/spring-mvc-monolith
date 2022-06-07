function update() {
    $.ajax({
        type: 'GET',
        url: '/cart/status',
        dataType: 'json',
        async: false,
        success: function (cart) {
            $("#cart-counter").text(cart.totalAmount);
            $("#cart-total-price").text(cart.totalPrice);
            cart.productAmounts.forEach(productAmount => {
                $("#" + productAmount.product.productId).text(productAmount.amount)
            })
            console.log(cart);
        },
        error: function (error) {
            console.log(error);
        }
    });
}

$('#add-to-cart').click(function () {
    $.ajax({
        type: 'PUT',
        url: '/cart/add/' + $(this).val(),
        dataType: 'json',
        async: false,
        error: function (error) {
            console.log(error);
        }
    });

    update();

    location.href = '/cart/'
});

$('#remove-from-cart').click(function () {
    $.ajax({
        type: 'DELETE',
        url: '/cart/remove/' + $(this).val(),
        dataType: 'json',
        async: false,
        error: function (error) {
            console.log(error);
        }
    });

    update();
});

$('.increment-to-cart').click(function () {
    var productId = $(this).val()
    $.ajax({
        type: 'PATCH',
        url: '/cart/increment/' + productId,
        dataType: 'json',
        async: false,
        error: function (error) {
            console.log(error);
        }
    });

    update();
});

$('.decrement-to-cart').click(function () {
    var productId = $(this).val()
    $.ajax({
        type: 'PATCH',
        url: '/cart/decrement/' + productId,
        dataType: 'json',
        async: false,
        error: function (error) {
            console.log(error);
        }
    });

    update();
});

update();