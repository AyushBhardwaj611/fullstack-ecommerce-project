import { useContext } from 'react';
import Cart from '../../components/Cart';
import CartContext from '../../Context/CartContext';

function CartPage() {
  const { cart } = useContext(CartContext);
  
  return (
    <Cart cart={cart} />
  );
}

export default CartPage;