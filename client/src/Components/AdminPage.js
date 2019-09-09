import React, {useEffect, useState} from 'react';
import axios from 'axios';
import ProductDetails from './ProductDetails';

const AdminPage = ({products, refresh, setRefresh, name, setName, price, setPrice, onInputChange}) => {

    const renderContent = () => {
        
        return products.map( product => {
           const { id, name, price, isInEditMode } = product;
           return (
                    <ProductDetails 
                        deleteProduct = {deleteProduct} prod_id = {id} key = {id} 
                        name = {name} price = {price} button = "delete" 
                    />
           );
        })
     }

     async function deleteProduct(id) {
        const response = await axios.delete(`http://localhost:8080/products/${id}`);
        setRefresh(!refresh);
        return response;
    };

    async function editProduct() {

        let params = {
            "name": name,
            "price": price
        };
        const response = await axios.put(`http://localhost:8080/products/${name}`,
            params
        );
        setRefresh(!refresh);
        return response;
    }

     async function postProduct() {

        let params = {
            "name": name,
            "price": price
        };
        const response = await axios.post("http://localhost:8080/products",
            params 
        )
        setRefresh(!refresh);
        return response;
    };

    const onClickEdit = (event) => {
        event.preventDefault();
        editProduct();
    }  

    const onClickAdd = (event) => {
        event.preventDefault();
        postProduct();
    }   

    return (
        <div>
            <h1>Admin Page</h1>
            <div className = "row">
                {renderContent()}
            </div>
            <div className = "row">
                <form>
                    <div className="form-row">
                        <div className="form-group col-md-16 mb-3">
                            <label>Product name</label>
                            <input id = "name" type="text" onChange = {onInputChange} className="form-control" placeholder="Enter Product Name" />
                        </div>
                    </div>
                    <div className="form-row">
                        <div className="form-group col-md-8 mb-3">
                            <label>Price</label>
                            <input id = "price" type="number" onChange = {onInputChange} className="form-control" placeholder="Enter Price" />
                        </div>
                    </div>
                    <button style = {{margin: 20}} onClick = {onClickEdit} type="submit" className="btn btn-primary">Edit Product</button>
                    <button onClick = {onClickAdd} type="submit" className="btn btn-primary">Add New Product</button>
                </form>
            </div>
        </div>
    );
};

export default AdminPage;