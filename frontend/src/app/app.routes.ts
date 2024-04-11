import { Routes } from '@angular/router';
import {CartComponent} from "./cart/cart.component";
import {HomeComponent} from "./pages/home/home.component";
import {ProductPageComponent} from "./product/product-page/product-page.component";
import {RegisterComponent} from "./auth/register/register.component";
import {LoginComponent} from "./auth/login/login.component";
import {AuthGuard} from "./auth/logic/auth.guard";
import {CheckoutComponent} from "./checkout/checkout.component";
import {AllOrdersComponent} from "./pages/all-orders/all-orders.component";
import {OrderComponent} from "./pages/order/order.component";
import {AdminDashComponent} from "./admin/admindash/admindash.component";
import {AdminProductsComponent} from "./admin/adminproducts/adminproducts.component";

/**
 * The routes for the application.
 * @type {Routes}
 */
export const routes: Routes = [
  /**
   * Route for the home page.
   */
  { path: '', component: HomeComponent },

  /**
   * Route for the cart page.
   */
  { path: 'cart', component: CartComponent },

  /**
   * Route for the product page. The id of the product is a route parameter.
   */
  { path: 'product/:id', component: ProductPageComponent },

  /**
   * Route for the register page.
   */
  { path: 'register', component: RegisterComponent },

  /**
   * Route for the login page.
   */
  { path: 'login', component: LoginComponent},

  /**
   * Route for the checkout page. This route is protected by the AuthGuard.
   */
  { path: 'checkout', component: CheckoutComponent, canActivate: [AuthGuard] },

  /**
   * Route for the orders page. This route is protected by the AuthGuard.
   */
  { path: 'orders', component: AllOrdersComponent, canActivate: [AuthGuard]},

  /**
   * Route for the single order page. The id of the order is a route parameter. This route is protected by the AuthGuard.
   */
  { path: 'order/:id', component: OrderComponent, canActivate: [AuthGuard]},

  { path: 'admin', component: AdminDashComponent},
  { path: 'admin/products', component: AdminProductsComponent },
  // { path: 'admin', component: AdminDashComponent, canActivate: [AuthGuard] },
  // { path: 'admin', component: AdminDashComponent, canActivate: [AuthGuard] },
  // { path: 'admin', component: AdminDashComponent, canActivate: [AuthGuard] },
  // { path: 'admin', component: AdminDashComponent, canActivate: [AuthGuard] },




  /**
   * Route for unmatched paths. Redirects to the home page.
   */
  { path: '**', redirectTo: '' }
];
