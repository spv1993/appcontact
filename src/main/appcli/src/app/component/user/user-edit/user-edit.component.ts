import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

import { UserService } from '../../../service/user/user.service';
import { User } from '../../../model/user';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

	user: any = {};

	sub: Subscription;
	
  constructor(private route: ActivatedRoute,
              private router: Router,
              private userService: UserService) { 
	}

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.userService.get(id).subscribe((user: any) => {
		  console.log("get user");
		  console.log(user);
          if (user) {
			console.log("init");
			console.log(user);
            this.user = user;
          } else {
            console.log(`User with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }
	
	ngOnDestroy() {
    this.sub.unsubscribe();
  }

	gotoList() {
    this.router.navigate(['/users']);
  }
	
	save(user: User) {
		console.log("save");
		console.log(user);
    this.userService.save(user).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }
	
	remove(id) {
		console.log("remove id");
		console.log(id);
    this.userService.remove(id).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }
}
