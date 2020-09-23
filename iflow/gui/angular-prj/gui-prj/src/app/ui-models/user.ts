import { UserAccessType } from './user-access';
import { UserDepartment } from './user-department';

﻿export class User {
	id: string;
	companyId: string;
	identity: string;
	birthDate: string;
	email: string;
  username: string;
	firstName: string;
  lastName: string;
	fullName: string;
	companyIdentity: string;
	userAccess: UserAccessType;
	userAccessLabel: string;
	password: string;

	status: number;
	version: number;

  createdAt: string;
  updatedAt: string;

	permission: string;

	userDepartments: UserDepartment[] = [];
	groups: string[] = [];
	deputies: string[] = [];
	roles: string[] = [];

}


