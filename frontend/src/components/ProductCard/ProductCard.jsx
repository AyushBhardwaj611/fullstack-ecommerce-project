import "./ProductCard.css";
import AddToCart from "../addToCart";

function ProductCard({product}) {
    
    // Removed the handleAddToCart function since AddToCart component handles it via context
    
    return (
        <div className="card">
            <div>{product.title}</div>
            <div>{product.price}</div>
            <AddToCart
                product={product}
        /*        cart={cart}
                addToCart={addToCart}
                removeFromCart={removeFromCart}. */

                // they are no more required because of the context api used directly
                // at the add to cart which is the deepest child right now
            />
        </div>
    );
}   

export default ProductCard;