import { Form } from '../../components';
import { registerForm } from '../../staticData';

export const Register = () => {
  return (
    <section>
      <Form
        title={registerForm.title}
        fields={registerForm.fields}
        buttonText={registerForm.buttonText}
      />
    </section>
  );
};
