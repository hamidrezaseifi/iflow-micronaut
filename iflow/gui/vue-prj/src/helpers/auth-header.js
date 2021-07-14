
export function authHeader() {
    // return authorization header with jwt token
    let user = JSON.parse(sessionStorage .getItem('user'));

    var headers = {'Content-Type': 'application/json'};

    if (user && user.accessToken) {
        headers['Authorization'] =  'Bearer ' + user.accessToken;
    }

    return headers;
}

export function createRequestOptions(requestMethod, useCredentials, requestBody) {
    // return authorization header with jwt token
    const requestOptions = {
            method: requestMethod,
            //withCredentials: true,
            //credentials: 'include',
            credentials: 'include',
            headers: authHeader()
    };

    if(useCredentials){
        requestOptions['credentials'] = 'include';
    }
    else{
        requestOptions['credentials'] = 'include';
    }

    if(requestBody && requestMethod == "POST"){
        requestOptions['body'] = requestBody;
    }



    return requestOptions;
}
