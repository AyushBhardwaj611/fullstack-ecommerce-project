import CartTotal from "../CartTotal";
import CartContext from "../../Context/CartContext";
import { useContext } from "react";

function Cart() {
    const { cart } = useContext(CartContext);
    let cartList = cart ? Object.values(cart) : [];

    if (cartList.length === 0) {
        return <div className="cart">Your cart is empty</div>;
    }
    else {
        return (
            <div className="cart">
            <ul>
                {cartList.map((item) => (
                    <li key={item.id}>
                        {item.title} - ${item.price} x {item.quantity}
                    </li>
                ))}
            </ul>
            <CartTotal cartItems={cartList} />
        </div>

        );
    }
}

export default Cart;
