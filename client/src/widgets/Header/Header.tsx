import { Logo, Nav } from "../../components";

export const Header = () => {
  return (
    <header className="header">
      <div className="shell">
        <div className="header__inner">
          <Logo />

          <Nav />
        </div>
      </div>
    </header>
  );
};
