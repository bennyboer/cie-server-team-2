import {
  MatButtonModule, MatCardModule,
  MatCheckboxModule,
  MatIconModule, MatInputModule,
  MatMenuModule,
  MatSidenavModule,
  MatToolbarModule
} from '@angular/material';
import {NgModule} from '@angular/core';

@NgModule({
  imports: [MatButtonModule, MatCheckboxModule, MatToolbarModule, MatSidenavModule, MatMenuModule, MatIconModule, MatCardModule, MatInputModule],
  exports: [MatButtonModule, MatCheckboxModule, MatToolbarModule, MatSidenavModule, MatMenuModule, MatIconModule, MatCardModule, MatInputModule],
})
export class CustomMaterialModule {
}
