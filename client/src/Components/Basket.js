import React, {useEffect, useState} from 'react';
import axios from 'axios';
import ProductDetails from './ProductDetails';

const Basket = ({basket, setBasket, user, basketo, products, refresh, setRefresh}) => {

    var basketop = [];
    var listbaskets = [];
    if (basketo != undefined) {
        basketo.map(prod => {
            if (prod != undefined) {
                products.map(p => {
                    if (p.name == prod.name) {
                        prod = p;
                        basketop.push(prod);
                    }
                })
            }
        })
    }

    async function updateBasket(items) {
        const response = await axios.put(`http://localhost:8080/basket/${user.user.id}`,
        { 
            headers: {
                'Content-Type': 'application/json',
            },
            products: [
                ...items
            ]
        }
        )
        setRefresh(!refresh);
        return response;
    }
    
    const removeProductFromBasket = (name) => {
        listbaskets = [];
        basketo.map(prod => {
            if (prod.name != name) {
                listbaskets.push(prod);
            }
        });
        updateBasket(listbaskets);
    }
    const renderContent = () => {
        
         return basketop.map( product => {
           const { id, name, price} = product;
           return (
                    <ProductDetails 
                        key = {id} 
                        name = {name} 
                        price = {price} 
                        button = "remove"
                        removeProductFromBasket = {removeProductFromBasket}
                    />
           );
        }) 
     }
//todo make sure user can remove items from basket
    return (
        <div>
            <h1>Basket Page</h1>
            <a style = {{float: "right" }} href = "/commerce">Back to shopping</a>
            <div>
                {renderContent()}
            </div>
            <button>Checkout</button>
        </div>
    );
};

export default Basket;