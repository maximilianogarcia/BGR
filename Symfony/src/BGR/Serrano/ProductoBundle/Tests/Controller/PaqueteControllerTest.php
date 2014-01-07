<?php

namespace BGR\Serrano\ProductoBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class PaqueteControllerTest extends WebTestCase
{
    public function testGetcantidaddisponiblebypresentacionid()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/getCantidadDisponibleByPresentacionId');
    }

}
