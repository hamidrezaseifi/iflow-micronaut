import { UserAccessType } from './user-access';
import { UserDepartment } from './user-department';

﻿export class User {
	id: string = "";
	companyId: string = "";
	identity: string = "";
	birthDate: string = "";
	email: string = "";
  username: string = "";
	firstName: string = "";
  lastName: string = "";
	fullName: string = "";
	companyIdentity: string = "";
	userAccess: UserAccessType = UserAccessType.NONE;
	userAccessLabel: string = "";
	password: string = "";

	status: number = 0;
	version: number = 0;

  createdAt: string = "";
  updatedAt: string = "";

	permission: string = "";

	userDepartments: UserDepartment[] = [];
	groups: string[] = [];
	deputies: string[] = [];
	roles: string[] = [];

}


