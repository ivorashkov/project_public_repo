import { Route, Routes } from 'react-router-dom';
import { Header, Wrapper } from './widgets';
import { Footer } from './widgets/Footer';
import { AuthProvider } from './context';

import { Home, Register, Login, Logout, Dashboard, CreateArticle, EditArticle, Article } from './pages';

const App = () => {
  return (
    <AuthProvider>
      <Header />

      {/* <img 
          src={require('./files/user_4/building-g0dd0ec446_1280.jpg')} 
          alt="logo" 
      /> */}
    
      <Wrapper>
        <Routes>
          <Route index path="/" element={<Home />} />
          <Route index path="/register" element={<Register />} />
          <Route index path="/login" element={<Login />} />
          <Route index path="/logout" element={<Logout />} />
          <Route index path="/dashboard/*" element={<Dashboard />} />
          <Route index path="/offer/create" element={ <CreateArticle />} />
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
