import React from 'react';
import images from '../images';
import sisyphus from '../images/Sisyphus.jpg';

const ProductDetails = ({prod_id, name, price, image, button, putBasket, removeProductFromBasket, deleteProduct}) => {

    function onClickAdd() {
        putBasket(name);
    };

    function onClickRemove() {
        removeProductFromBasket(name);
    };

    function onClickDelete() {
        deleteProduct(prod_id);
    };

    switch(button) {
        case "add":
            return (
                <div className="col-sm-3">
                    <div style = {{marginBottom: 20}} className="card">
                        <img src={sisyphus} className="card-img-top" alt="..." />
                        <div className="card-body">
                            <h5 className="card-title">{name}</h5>
                            <p className="card-text">Price: £{price}</p>
                            <button id = {name} onClick = {onClickAdd}>Add to basket</button>
                        </div>
                    </div>
                </div>
            );
        case "remove": 
            return (
                <div className="col-sm-3">
                    <div  style = {{marginBottom: 20}} className="card">
                        <img src={sisyphus} className="card-img-top" alt="..." />
                        <div className="card-body">
                            <h5 className="card-title">{name}</h5>
                            <p className="card-text">Price: £{price}</p>
                            <button id = {name} onClick = {onClickRemove}>Remove from basket</button>
                        </div>
                    </div>
                </div>
            );
        case "delete":
                return (
                    <div style = {{marginBottom: 20}} className="col-sm-3">
                        <div  className="card">
                            <img src={sisyphus} className="card-img-top" alt="..." />
                            <div className="card-body">
                                <h5 className="card-title">{name}</h5>
                                <p className="card-text">Price: £{price}</p>
                                <button id = {name} onClick = {onClickDelete}>Delete</button>
                            </div>
                        </div>
                    </div>
                );
        default:
            return (
                <div style = {{marginBottom: 20}} className="col-sm-3">
                    <div  className="card">
                        <img src={sisyphus} className="card-img-top" alt="..." />
                        <div className="card-body">
                            <h5 className="card-title">{name}</h5>
                            <p className="card-text">Price: £{price}</p>
                        </div>
                    </div>
                </div>
            );
    }
}

export default ProductDetails;