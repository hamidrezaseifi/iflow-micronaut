import { User } from './user';

export class LoginResponse {
	timestamp: Number;
	exception:string;
	message:string;
	res:string;
	user:User;
}