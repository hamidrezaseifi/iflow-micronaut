
import { User } from './user';

export class Department {
	id: string = "";
	companyId: string = "";
	identity: string = "";
	title: string = "";
	status: number = 0;
	version: number = 0;
	manager: User = new User;
	deputy: User = new User;

}
