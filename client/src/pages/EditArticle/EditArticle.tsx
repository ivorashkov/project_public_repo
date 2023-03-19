import { Form } from '../../components';
import { articleFields } from '../../staticData';

export const EditArticle = () => {
  return (
    <section>
      <Form
        title="Edit Article"
        fields={articleFields.fields}
        buttonText={articleFields.buttonText}
      />
    </section>
  );
};
