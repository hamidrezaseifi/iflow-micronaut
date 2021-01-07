import { User } from './user';

export class LoginResponse {
	timestamp: Number = 0;
	exception:string = "";
	message:string = "";
	res:string = "";
	user:User = new User;
}
