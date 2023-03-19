export const registerForm = {
  title: 'Register',
  fields: [
    {
      type: 'fullName',
      label: 'Full name',
      name: 'fullName',
      testId: 'fullName'
    },
    {
      type: 'email',
      label: 'Email',
      name: 'email',
      testId: 'email'
    },
    {
      type: 'password',
      label: 'Password',
      name: 'password',
      testId: 'password'
    },
    {
      type: 're-password',
      label: 'Repeat Password',
      name: 're-password',
      testId: 're-password'
    }
  ],
  buttonText: 'submit'
};
