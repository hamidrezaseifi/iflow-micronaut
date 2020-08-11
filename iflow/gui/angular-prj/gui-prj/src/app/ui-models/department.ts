
import { User } from './user';

export class Department {
	companyIdentity: string;
	identity: string;
	title: string;
	status: number;
	version: number;
	manager: User = new User;
	deputy: User = new User;

}