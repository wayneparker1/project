import React, {useState, useEffect} from 'react';
import axios from 'axios';
import {BrowserRouter, Route} from 'react-router-dom';
import CommercePage from './Components/CommercePage';
import LoginPage from './Components/LoginPage';
import Basket from './Components/Basket';
import AdminPage from './Components/AdminPage';

import './App.css';

function App() {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [user, setUser] = useState();
  const [basket, setBasket] = useState();
  const [refresh, setRefresh] = useState(false);
  const [products, setProducts] = useState([]);

  
  if (user != undefined) {
    if (user.user.basket.products.length === 0) {
      var productNames = [];
  } else {
      var basketzz = user.user.basket.products[0].baskets[0].products.slice(1).map(prod => {return {name: prod.name}});
      var productNames = [];
      productNames = [
        {name: user.user.basket.products[0].name},
        ...basketzz
      ];
    };
  }
    
  async function putBasket(id) {
    if (!productNames.map(prod => prod.name).includes(id)) {
      productNames.push({name: id});
    }
      const response = await axios.put(`http://localhost:8080/basket/${user.user.id}`,
        { 
            headers: {
                'Content-Type': 'application/json',
            },
            products: [
                ...productNames
            ]
        }
    ) 
    
    setRefresh(!refresh);
    return response;      
};

  async function fetchUser() {

    //add template string for username
    const response = await axios.get("http://localhost:8080/users/bob",
        { 
            headers: {
                'Content-Type': 'application/json'
            }
        }
    );
    return response;
  };

  async function fetchProducts() {
      const response = await axios.get("http://localhost:8080/products",
          { 
              headers: {
                  'Content-Type': 'application/json',
              }
          }
      )
      return response;
  };

  const onInputChange = ({target}) => {

    if (target.id === "name") {
        setName(target.value);
    } else if (target.id === "userName") {
        setUserName(target.value);
    } else if (target.id === "password") {
        setPassword(target.value);
    }
        else {
        setPrice(target.value);
    }
}

  useEffect(
      () => {
          fetchProducts().then((response) => setProducts(response.data));
          fetchUser().then((response) => setUser({user: response.data}));
      }
      , []
  )

useEffect(
    () => {
        fetchProducts().then((response) => setProducts(response.data));
        fetchUser().then((response) => setUser({user: response.data}));
    }
    , [refresh]
)

  return (
    <div className="App container">
      <BrowserRouter>
        <Route path = "/commerce" exact render = {() => 
          <CommercePage 
            refresh = {refresh} setRefresh = {setRefresh}
            user = {user} products = {products} putBasket = {putBasket}
          />}
        />
        <Route path = "/" exact render = {() => 
          <LoginPage 
            user = {user} setUser = {setUser} onInputChange = {onInputChange}
            userName = {userName} password = {password} refresh = {refresh} setRefresh = {setRefresh}
          />} 
        />
        <Route path = "/basket" exact render = {() => 
          <Basket 
            basketo = {productNames} user = {user} 
            basket = {basket} setBasket = {setBasket} 
            products = {products} refresh = {refresh} setRefresh = {setRefresh}
          />}
        />
        <Route path = "/admin" exact render = {() => 
          <AdminPage 
            name = {name} setName = {setName} price = {price} setPrice = {setPrice} 
            refresh = {refresh} setRefresh = {setRefresh} products = {products} onInputChange = {onInputChange}
          />}
        />
      </BrowserRouter>
    </div>
  );
}

export default App;
