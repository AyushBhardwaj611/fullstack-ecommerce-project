function CartTotal({ cartItems }) {
    // Calculate total price
    let totalPrice = cartItems.reduce((total, item) => {
        return total + (parseFloat(item.price) * item.quantity);
    }, 0);

    return (
        <div className="cart-total">
            <strong>Total: ${totalPrice.toFixed(2)}</strong>
        </div>
    );
}

export default CartTotal;