import {
  MatButtonModule, MatCardModule,
  MatCheckboxModule,
  MatIconModule, MatInputModule,
  MatMenuModule, MatProgressSpinnerModule,
  MatSidenavModule, MatSnackBarModule, MatTableModule,
  MatToolbarModule
} from '@angular/material';
import {NgModule} from '@angular/core';

@NgModule({
  imports: [
    MatButtonModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatSidenavModule,
    MatMenuModule,
    MatIconModule,
    MatCardModule,
    MatInputModule,
    MatTableModule,
    MatSnackBarModule,
    MatProgressSpinnerModule
  ],
  exports: [
    MatButtonModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatSidenavModule,
    MatMenuModule,
    MatIconModule,
    MatCardModule,
    MatInputModule,
    MatTableModule,
    MatSnackBarModule,
    MatProgressSpinnerModule
  ]
})
export class CustomMaterialModule {
}
