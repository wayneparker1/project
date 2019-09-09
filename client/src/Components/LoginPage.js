import React, {useEffect, useState} from 'react';
import axios from 'axios';
import LoginCard from './LoginCard';

const LoginPage = ({user, setUser, userName, password, onInputChange, refresh, setRefresh}) => {
    console.log(user);

    async function postUser() {

        let params = {
            "userName": userName,
            "password": password
        };
        const response = await axios.post("http://localhost:8080/users",
            params 
        )
        setRefresh(!refresh);
        return response;
    };

     const onClickNewUser = (event) => {
        event.preventDefault();
        postUser();
    }  

    return (
        <div>
            <h1>Login Page</h1>
            <div style = {{width: "100%"}}>
                <LoginCard onClickNewUser = {onClickNewUser} onInputChange = {onInputChange} />
            </div>
            
        </div>
    );
};

export default LoginPage;