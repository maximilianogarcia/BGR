<?php

namespace BGR\Serrano\ProductoBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class ProductoControllerTest extends WebTestCase
{
    public function testGetall()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/getAll');
    }

    public function testGetbyid()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/getById');
    }

    public function testGetbycategoria()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/getByCategoria');
    }

}
