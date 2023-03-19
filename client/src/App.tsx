import { Route, Routes } from 'react-router-dom';
import { lazy, Suspense } from 'react';
import { Header, Wrapper } from './widgets';
import { Footer } from './widgets/Footer';
import { AuthProvider } from './context';

const Article = lazy(() => import('./pages').then((module) => ({ default: module.Article })));
const CreateArticle = lazy(() =>
  import('./pages').then((module) => ({ default: module.CreateArticle }))
);
const EditArticle = lazy(() =>
  import('./pages').then((module) => ({ default: module.EditArticle }))
);
const Dashboard = lazy(() => import('./pages').then((module) => ({ default: module.Dashboard })));
const Home = lazy(() => import('./pages').then((module) => ({ default: module.Home })));
const Register = lazy(() => import('./pages').then((module) => ({ default: module.Register })));
const Login = lazy(() => import('./pages').then((module) => ({ default: module.Login })));

const App = () => {
  return (
    <Suspense fallback={<h2>Loadign...</h2>}>
      <AuthProvider>
        <Header />

        <Wrapper>
          <Routes>
            <Route index path="/" element={<Home />} />
            <Route index path="/register" element={<Register />} />
            <Route index path="/login" element={<Login />} />
            <Route index path="/dashboard/*" element={<Dashboard />} />
            <Route index path="/blogs/edit/:id" element={<EditArticle />} />
            <Route index path="/blogs/create" element={<CreateArticle />} />
            <Route index path="/blogs/blog/:id" element={<Article />} />
            <Route path="*" element={<h1>404</h1>} />
          </Routes>
        </Wrapper>

        <Footer />
      </AuthProvider>
    </Suspense>
  );
};

export default App;
