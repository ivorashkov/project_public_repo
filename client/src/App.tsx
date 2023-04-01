import { Route, Routes } from 'react-router-dom';
import { lazy, Suspense } from 'react';
import { Header, Wrapper } from './widgets';
import { Footer } from './widgets/Footer';
import { AuthProvider } from './context';

// const Article = lazy(() => import('./pages').then((module) => ({ default: module.Article })));
// const CreateArticle = lazy(() =>
//   import('./pages').then((module) => ({ default: module.CreateArticle }))
// );
// const EditArticle = lazy(() =>
//   import('./pages').then((module) => ({ default: module.EditArticle }))
// );
// const Dashboard = lazy(() => import('./pages').then((module) => ({ default: module.Dashboard })));
// const Home = lazy(() => import('./pages').then((module) => ({ default: module.Home })));
// const Register = lazy(() => import('./pages').then((module) => ({ default: module.Register })));
// const Login = lazy(() => import('./pages').then((module) => ({ default: module.Login })));
// const Logout = lazy(() => import('./pages').then((module) => ({ default: module.Logout })));

import { Home, Register, Login, Logout, Dashboard, CreateArticle, EditArticle, Article } from './pages';

const App = () => {
  return (
    <AuthProvider>
      <Header />
    
      <Wrapper>
        <Routes>
          <Route index path="/" element={<Home />} />
          <Route index path="/register" element={<Register />} />
          <Route index path="/login" element={<Login />} />
          <Route index path="/logout" element={<Logout />} />
          <Route index path="/dashboard/*" element={<Dashboard />} />
          <Route index path="/offers/create" element={<CreateArticle />} />
          <Route index path="/offers/edit/:id" element={<EditArticle />} />
          <Route index path="/offers/offer/:offerId" element={<Article />} />
          <Route path="*" element={<h1>404</h1>} />
        </Routes>
      </Wrapper>

      <Footer />
    </AuthProvider>
  );
};

export default App;
