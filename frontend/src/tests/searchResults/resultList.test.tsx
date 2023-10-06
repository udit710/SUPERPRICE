import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import ResultList from '../../components/searchResults/resultList';
import { Product } from '../../interfaces/product.interface';

describe('ResultsList comonent tests', () => {
    const testProduct: Product = {
        id: 1,
        productName: 'test',
        description: 'test',
        subCategoryId: 1,
        details: [{
            id: 1,
            store: {storeName: ''},
            price: 1,
            available: 1,
            discount: 1,
            original_price: 1,
        }],
        images: [{imageUrl: ''}]
    };

    const view = render(<ResultList products={[testProduct]}/>);
    expect(view).toBeTruthy();

    test('Check image exists', () => {
        const img = screen.getAllByAltText('product');
        expect(img.length).greaterThanOrEqual(1);
    });

    test('Check name exists', async () => {
        const name = await screen.findAllByTestId('product_name');
        expect(name.length).greaterThanOrEqual(1);
    });

    test('Check price exists', async () => {
        const price = await screen.findAllByTestId('price');
        expect(price.length).greaterThanOrEqual(1);
    });

});
