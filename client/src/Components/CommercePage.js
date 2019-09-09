import React, {useEffect, useState} from 'react';
import axios from 'axios';
import ProductDetails from './ProductDetails';

const CommercePage = ({products, refresh, setRefresh, user, setUser, fetchUser, putBasket}) => {
    
    const renderContent = () => {
        
        return products.map( product => {
           const { id, name, price} = product;
           return (
                    <ProductDetails putBasket = {putBasket} key = {id} name = {name} price = {price} button = "add"/>
           );
        })
     }
    return (
        <div>
            <h1>eCommerce Page</h1>
            <a style = {{float: "right" }} href = "/basket">Basket</a>
            <div className = "row">
                {renderContent()}
            </div>
        </div>
    );
};

export default CommercePage;