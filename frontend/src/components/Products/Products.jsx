import { useState, useEffect, memo } from "react";
import ProductCard from "../ProductCard";  
import { Link } from "react-router-dom";

const gp = [
  {
    title: "Apple iPhone 14",
    price: "Rs. 1,00,000",
  },
  {
    title: "Apple iPhone 13",
    price: "Rs. 70,000",
  },
  {
    title: "Google Pixel 7",
    price: "Rs. 50,000",
  },
  {
    title: "Nokia 1100",
    price: "Rs. 2,000",
  },
  {
    title: "Samsung Galaxy S10",
    price: "Rs. 1,00,000",
  },
  {
    title: "Sony Xperia S10",
    price: "Rs. 1,00,000",
  },
];

function getProductsApi(callback) {
  setTimeout(function() {
    console.log("API called");
    
    callback(gp);
  }, 2000);
}

 function Products(/* cart, addToCart, removeFromCart */) {

  let [products, setProducts] = useState([]);
  let [loading, setLoading] = useState(true);

  useEffect(function() {
    console.log("useEffect called");
    
  /*  getProductsApi(function(global) {
      console.log("callback called");
      setProducts(global);
      setLoading(false);
    }); */

    fetch("https://api.escuelajs.co/api/v1/products")
      .then(response => {
        return response.json();
      })
      .then(response => {
        console.log(response);
        setProducts(response);
        setLoading(false);
      })
      .catch(error => {
        console.log("Error fetching products:", error);
        setLoading(false);
      });

    console.log("API call initiated");
  }, []); // Empty dependency array to run only once on mount
  // This will ensure the API is called only once when the component mounts
  console.log("api completed");
    return (
        <div>
          <Link to="/cart">Cart</Link>
            {loading ? (
                <p>Loading...</p>
            ) : (
                products.map(function (item) {

                    return <ProductCard key={item.id}
                      product={item}
                /*      cart={cart}
                      addToCart={addToCart}
                      removeFromCart={removeFromCart} */ />;  
                })
            )}
        </div>
    );
}

export default memo(Products);

// NOTE --> anchor tag loses the state when we add items to the cart..and cart shows Empty
//         so we use the link tag from react router dom to preserve the state