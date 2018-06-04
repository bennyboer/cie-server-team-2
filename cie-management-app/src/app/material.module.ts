import {
  MatButtonModule, MatCardModule,
  MatCheckboxModule, MatDialogModule,
  MatIconModule, MatInputModule,
  MatMenuModule, MatProgressSpinnerModule, MatSelectModule,
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
    MatProgressSpinnerModule,
    MatDialogModule,
    MatSelectModule
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
    MatProgressSpinnerModule,
    MatDialogModule,
    MatSelectModule
  ]
})
export class CustomMaterialModule {
}
