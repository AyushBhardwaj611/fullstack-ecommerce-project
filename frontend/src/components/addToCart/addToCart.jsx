import CartContext from '../../Context/CartContext';
import { useContext } from "react";


function AddToCart({ product }) {

    const { cart, addToCart, removeFromCart } = useContext(CartContext);

    function increment() {
        console.log("Incrementing cart");
        if (addToCart && product) {
            addToCart(product);
        }
    }

    function decrement() {
        console.log("Decrementing cart");
        if (removeFromCart && product) {
            removeFromCart(product);
        }
    }

    // Safely compute quantity
    const quantity = product?.id && cart[product.id]?.quantity
        ? cart[product.id].quantity
        : 0;

    if (quantity > 0) {
        return (
            <div>
                <button onClick={decrement}>-</button>
                <span>{quantity}</span>
                <button onClick={increment}>+</button>
            </div>
        );
    } else {
        return (
            <div>
                <button onClick={increment}>Add To Cart</button>
            </div>
        );
    }
}

export default AddToCart;
