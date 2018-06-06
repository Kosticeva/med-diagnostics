import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { pageRoutes } from './app.route';

@NgModule({
  imports: [
    RouterModule.forRoot(pageRoutes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}