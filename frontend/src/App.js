import React, { useState, useEffect, useContext } from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ProductsPage from './Pages/ProductsPage';
import CartPage from './Pages/CartPage';
import CartContext from "./Context/CartContext";
import './App.css';


function App() {
  const [cart, setCart] = useState({});

  function addToCart(product) {
    if (!product || !product.id) return;
    const newCart = { ...cart };
    if (!newCart[product.id]) newCart[product.id] = { ...product, quantity: 1 };
    else newCart[product.id].quantity += 1;
    setCart(newCart);
  }

  function removeFromCart(product) {
    if (!product || !product.id) return;
    const newCart = { ...cart };
    if (!newCart[product.id]) return;
    newCart[product.id].quantity -= 1;
    if (newCart[product.id].quantity <= 0) delete newCart[product.id];
    setCart(newCart);
  }

  return (
    <div className="App">
      <CartContext.Provider value={{ cart, addToCart, removeFromCart }}>
        <Router>
          <Routes>
            <Route path="/" element={<ProductsPage />} />
            <Route path="/cart" element={<CartPage />} />
          </Routes>
        </Router>
      </CartContext.Provider>
    </div>
  );
}

export default App;
