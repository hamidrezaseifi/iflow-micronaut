//import config from 'config';
import { createRequestOptions } from '../helpers';

export const userService = {
    login,
    logout,
    getAll1
};

function login(username, password) {
    const requestOptions = createRequestOptions('POST', false, JSON.stringify({ username, password }));

    return fetch(process.env.VUE_APP_loginUrl, requestOptions)
        .then(handleResponse)
        .then(user => {
            console.log("userloggedin user" , user);
            if (user.username && user.active) {
                sessionStorage.setItem('user', JSON.stringify(user));
            }

            return user;
        }).catch(function(err) {
              console.log('Fetch Error', err);
              return Promise.reject(err);
        });
}

function logout() {
    // remove user from local storage to log user out
    sessionStorage .removeItem('user');
}

function getAll1() {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_apiUrl + '/users', requestOptions).then(handleResponse);
}

function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            if (response.status === 401) {
                // auto logout if 401 response returned from api
                //logout();
                //location.reload(true);
            }

            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }

        return data;
    });
}
