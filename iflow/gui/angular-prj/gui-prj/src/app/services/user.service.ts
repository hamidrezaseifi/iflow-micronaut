import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHepler } from '../helper/http-hepler';

import { User } from '../ui-models';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<User[]>(HttpHepler.dataServer + "/users");
    }

    register(user: User) {
        return this.http.post(HttpHepler.dataServer + "/users/register", user);
    }

    delete(id: number) {
        return this.http.delete(HttpHepler.dataServer + "/users/${id}");
    }

}
