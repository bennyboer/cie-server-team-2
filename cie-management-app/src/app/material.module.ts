import {
  MatButtonModule, MatCardModule,
  MatCheckboxModule,
  MatIconModule, MatInputModule,
  MatMenuModule,
  MatSidenavModule, MatTableModule,
  MatToolbarModule
} from '@angular/material';
import {NgModule} from '@angular/core';

@NgModule({
  imports: [MatButtonModule, MatCheckboxModule, MatToolbarModule, MatSidenavModule, MatMenuModule, MatIconModule, MatCardModule, MatInputModule, MatTableModule],
  exports: [MatButtonModule, MatCheckboxModule, MatToolbarModule, MatSidenavModule, MatMenuModule, MatIconModule, MatCardModule, MatInputModule, MatTableModule],
})
export class CustomMaterialModule {
}
