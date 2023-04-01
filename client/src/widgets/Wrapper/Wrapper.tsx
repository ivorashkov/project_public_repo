import { FC } from 'react';

interface IWrapper {
  children: React.ReactNode;
}

export const Wrapper: FC<IWrapper> = ({ children }) => {
  return (
    <main>
      <div className="wrapper">
        <div className="wrapperInner">
          <div className="shell">{children}</div>
        </div>
      </div>
    </main>
  );
};
