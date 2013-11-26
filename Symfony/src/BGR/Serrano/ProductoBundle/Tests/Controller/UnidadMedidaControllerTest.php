<?php

namespace BGR\Serrano\ProductoBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class UnidadMedidaControllerTest extends WebTestCase
{
    public function testGetall()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/unidadMedida/getAll');
    }

    public function testSave()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/unidadMedida/save');
    }

    public function testDelete()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/unidadMedida/delete');
    }

    public function testUpdate()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/unidadMedida/update');
    }

}
