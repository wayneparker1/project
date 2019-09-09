import React, {useState} from 'react';

const LoginCard = ({onClickNewUser, onInputChange}) => {

    return (
        <div style = {{margin: "auto"}}>
            <form>
                <div className="form-row">
                    <div className="form-group col-md-4 mb-3">
                        <label>UserName</label>
                        <input id = "userName" onChange = {onInputChange} type="text" className="form-control" placeholder="Enter UserName" />
                    </div>
                </div>
                <div className="form-row">
                    <div className="form-group col-md-4 mb-3">
                        <label>Password</label>
                        <input id = "password" onChange = {onInputChange} type="password" className="form-control" placeholder="Password" />
                    </div>
                </div>
                <button style = {{marginRight: 10}} type="submit" className="btn btn-primary">Login</button>
                <button onClick = {onClickNewUser} className="btn btn-secondary">New User</button>
            </form>
        </div>
    );
};

export default LoginCard;