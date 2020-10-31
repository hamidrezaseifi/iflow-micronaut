
import { User } from './user';

export class Department {
	id: string;
	companyIdentity: string;
	identity: string;
	title: string;
	status: number;
	version: number;
	manager: User = new User;
	deputy: User = new User;

}
