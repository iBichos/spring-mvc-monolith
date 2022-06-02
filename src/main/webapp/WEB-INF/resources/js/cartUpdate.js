function updateCart() {
    $.ajax({
        type: 'GET',
        url: '/cart/status',
        dataType: 'json',
        async: false,
        success: function (data) {
            $("#cart-counter").text(data.totalAmount);
            $("#cart-total-price").text(data.totalPrice);
            console.log(data);
        },
        error: function (data) {
            console.log(data);
        }
    });
}

$('#add-to-cart').click(function () {
    $.ajax({
        type: 'PUT',
        url: '/cart/add/' + $(this).val(),
        dataType: 'json',
        async: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.log(data);
        }
    });

    updateCart();

    location.href = '/cart/'
});

$('#remove-from-cart').click(function () {
    $.ajax({
        type: 'DELETE',
        url: '/cart/remove/' + $(this).val(),
        dataType: 'json',
        async: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.log(data);
        }
    });

    updateCart();
});

$('.increment-to-cart').click(function () {
    var productId = $(this).val()
    $.ajax({
        type: 'PATCH',
        url: '/cart/increment/' + productId,
        dataType: 'json',
        async: false,
        success: function (data) {
            $("#" + productId).text(data.productsIdsAndAmounts[productId])
        },
        error: function (data) {
            console.log(data);
        }
    });

    updateCart();
});

$('.decrement-to-cart').click(function () {
    var productId = $(this).val()
    $.ajax({
        type: 'PATCH',
        url: '/cart/decrement/' + productId,
        dataType: 'json',
        async: false,
        success: function (data) {
            $("#" + productId).text(data.productsIdsAndAmounts[productId])
        },
        error: function (data) {
            console.log(data);
        }
    });

    updateCart();
});

updateCart();